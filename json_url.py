import requests
import json
try:
    url = requests.get("https://api.github.com")
    data = url.json()
    data["current_user_url"] = "https://abc.github.com"
    print(data["current_user_url"])
    with open("status.json", "w") as f:
        json.dump(data,f)
except:
    print("The URL is not available")