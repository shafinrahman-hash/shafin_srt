import json

def update_json_field(file_path, field, new_value):
    with open(file_path, 'r') as f:
        data = json.load(f)
    
    if field in data:
        data[field] = new_value
    else:
        print(f"Field '{field}' not found. Adding it.")
        data[field] = new_value
    
    with open(file_path, 'w') as f:
        json.dump(data, f, indent=4)


update_json_field("data.json", "status", "completed")