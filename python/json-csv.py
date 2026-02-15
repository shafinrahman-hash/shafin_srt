import json
import csv

# Read JSON from a file
with open("input.json", "r") as json_file:
    json_data = json.load(json_file)

# Write JSON data to CSV
with open("output.csv", "w", newline="") as csv_file:
    writer = csv.DictWriter(csv_file, fieldnames=json_data[0].keys())
    writer.writeheader()
    writer.writerows(json_data)

print("JSON → CSV saved as output.csv")