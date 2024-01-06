import sys
import requests


def update_dns_record(token, rrset_value):
    url = "https://api.gandi.net/v5/livedns/domains/froissant.work/records/%2A.groupe6"

    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json",
    }

    data = {
        "items": [
            {
                "rrset_type": "A",
                "rrset_values": [rrset_value],
                "rrset_ttl": 300
            }
        ]
    }

    try:
        response = requests.put(url, headers=headers, json=data)

        if response.status_code == 201:
            print("Requête réussie !")
        else:
            print(
                f"Erreur lors de la requête. Code d'erreur : {response.status_code}")
            print(response.text)

    except Exception as e:
        print(f"Une erreur s'est produite : {e}")


if __name__ == "__main__":
    # Vérifier que le nombre d'arguments est correct
    if len(sys.argv) != 3:
        print("Usage: python nom_du_script.py <token_gandi> <ip>")
        sys.exit(1)

    # Récupérer les valeurs des arguments
    token_gandi = sys.argv[1]
    ip = sys.argv[2]

    update_dns_record(token_gandi, ip)