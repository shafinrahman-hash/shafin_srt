import json

def json_validate(data_json):
    with open(data_json, "r") as f:
        print(f)        
        try:
            json.load(f)
            return True
        except:
            return False

def json_parsor(data_json):
    with open(data_json) as f:
        data = json.load(f)
    print(data)
    print(data["user"]["name"])

if json_validate("data.json") == True:
    print("The json is Valid")
else:
    print("The json is not valid")

json_parsor("data.json")

