import requests
import osmnx as ox
from tabulate import tabulate
import mysql.connector

# Clé API TomTom Traffic
tomtom_traffic_api_key = 'cdhh3Mf1l0ALGrtUeRvgEnkZ0eXC6d6A'

def get_traffic_information(api_url, params):
    try:
        # Ajouter la clé API aux paramètres de la requête
        params['key'] = tomtom_traffic_api_key

        # Effectuer une requête GET à l'API TomTom Traffic
        response = requests.get(api_url, params=params)

        # Vérifier si la requête a réussi (code 200)
        if response.status_code == 200:
            # Obtenir la réponse JSON
            result_json = response.json()

            # Exclure les coordonnées si présentes
            if 'coordinates' in result_json['flowSegmentData']:
                del result_json['flowSegmentData']['coordinates']

            # Récupérer les informations de trafic
            current_speed = result_json['flowSegmentData']['currentSpeed']
            free_flow_speed = result_json['flowSegmentData']['freeFlowSpeed']

            # Déterminer l'état du trafic
            if current_speed >= free_flow_speed:
                etat_trafic = 'fluide'
            else:
                etat_trafic = 'ralenti'

            return etat_trafic

        else:
            print(f"Erreur de requête : {response.status_code}")
            print(response.text)  # Afficher le texte de la réponse pour obtenir plus de détails

    except Exception as e:
        print(f"Erreur : {e}")


def geocode(lat, lon):
    # Définir l'URL de l'API de géocodage de TomTom
    api_url_geocoding = "https://api.tomtom.com/search/2/reverseGeocode.json"

    # Paramètres de la requête
    params_geocoding = {
        'key': tomtom_traffic_api_key,
        'position': f'{lat},{lon}'
    }

    try:
        # Effectuer une requête GET à l'API de géocodage de TomTom
        response = requests.get(api_url_geocoding, params=params_geocoding)

        # Vérifier si la requête a réussi (code 200)
        if response.status_code == 200:
            # Obtenir la réponse JSON
            result_json = response.json()

            # Récupérer des informations spécifiques (nom de la rue, ville, municipalité)
            street = result_json['addresses'][0]['address']['streetName']
            city = result_json['addresses'][0]['address']['municipality']
            municipality = result_json['addresses'][0]['address']['localName']

            # Retourner les informations extraites sous forme de dictionnaire
            return {
                'street': street,
                'city': city,
                'municipality': municipality
            }

        else:
            print(f"Erreur de requête : {response.status_code}")
            print(response.text)  # Afficher le texte de la réponse pour obtenir plus de détails

    except Exception as e:
        print(f"Erreur : {e}")

# Exemple d'utilisation avec l'API TomTom Traffic pour un point spécifique
api_url_traffic = "https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json"
params_traffic = {
    'point': '48.873018,2.295032',  # Coordonnées d'un point (latitude,longitude)
    'unit': 'KMPH',
}

# Créer un tableau pour stocker les résultats
resultats_traffic = []

# Définir le nom de la rue
nom_rue = "Champs-Élysées, Paris, France"

# Obtenir le graphe de la rue depuis OSM
graph_rue = ox.graph_from_place(nom_rue, network_type="all")

# Extraire les nœuds (points) de la rue
nœuds_rue = ox.graph_to_gdfs(graph_rue, edges=False, nodes=True)

# Afficher uniquement les colonnes 'latitude' et 'longitude'
coordonnées_rue = nœuds_rue[['y', 'x']]

# Afficher les coordonnées
print(coordonnées_rue)

# Appeler la fonction get_traffic_information pour chaque point
for index, point in coordonnées_rue.head(100).iterrows():
    latitude = point['y']
    longitude = point['x']

    # Appeler la fonction get_traffic_information avec les coordonnées du point actuel
    params_traffic['point'] = f'{latitude},{longitude}'
    etat_trafic = get_traffic_information(api_url_traffic, params_traffic)

    resultat_geocodage = geocode(latitude, longitude)

    # Créer un dictionnaire pour stocker les résultats pour ce point
    resultat_point = {
        'latitude': latitude,
        'longitude': longitude,
        'etat_trafic': etat_trafic,
        'rue': resultat_geocodage['street'],
        'ville': resultat_geocodage['city'],
        'municipalité': resultat_geocodage['municipality']
    }

    # Ajouter le dictionnaire au tableau
    resultats_traffic.append(resultat_point)

# Afficher le tableau avec tabulate
tableau = tabulate(resultats_traffic, headers='keys', tablefmt='pretty')
print(tableau)

# Configuration de la connexion à la base de données MySQL
config = {
    'user': 'root',
    'password': '',
    'host': 'localhost',
    'database': 'traffic',
    'raise_on_warnings': True,
}

# Établir la connexion à la base de données
connexion = mysql.connector.connect(**config)

# Créer un objet curseur pour exécuter des requêtes SQL
curseur = connexion.cursor()

# Nom de la table dans la base de données
table_name = 'traffic'

# Créer la table si elle n'existe pas déjà
create_table_query = f"CREATE TABLE IF NOT EXISTS {table_name} (id INT PRIMARY KEY AUTO_INCREMENT, latitude float, longitude float, etat VARCHAR(255), nom_rue VARCHAR(255), ville VARCHAR(255), municipalite VARCHAR(255))"
curseur.execute(create_table_query)

# Insérer les données dans la table
for resultat in resultats_traffic:
    curseur.execute(f"INSERT INTO {table_name} (latitude, longitude, etat, nom_rue, ville, municipalite) VALUES (%s, %s, %s, %s, %s, %s)"
                    ,(resultat['latitude'], resultat['longitude'], resultat['etat_trafic'], resultat['rue'], resultat['ville'], resultat['municipalité']))

# Valider la transaction
connexion.commit()

# Fermer le curseur et la connexion
curseur.close()
connexion.close()