import csv
import json

with open("output.csv", "r") as f:
    reader = csv.DictReader(f)
    data = list(reader)

# Save JSON
with open("output.json", "w") as f:
    json.dump(data, f, indent=4)

print("CSV → JSON saved as output.json")