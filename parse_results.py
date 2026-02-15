results = [
    {
        'data': {
            'destination': 'LGW',
            'origin': 'JFK',
            'arrival_time': '17:50',
            'departure_time': '18:00',
            'price': '432GBP',
        },
        'source': 'duffel_air'
    },
    {
        'data': {
            'flights': 'JFK/LGW',
            'arrival_time': '17:50',
            'departure_time': '18:00',
            'price_fractional': '12300',
            'currency_code': 'GBP',
        },
        'source': 'virgin'
    },
    {
        'data': {
            'flights': 'JFK/LGW',
            'arrival_time': '19:30',
            'departure_time': '15:00',
            'price_fractional': '10900',
            'currency_code': 'GBP',
        },
        'source': 'virgin'
    },
    {
        'data': {
            'destination_code': 'lgw',
            'origin_code': 'jfk',
            'arrival_datetime': '2020-07-23 12:07:53 +0000',
            'departure_time': '2020-07-23 09:01:20 +0000',
            'price': {
                'code': 'USD',
                'value': '325.50'
            },
        },
        'source': 'aa'
    },
    {
        'data': {
            'destination_code': 'lgw',
            'origin_code': 'jfk',
            'duration': '542',
            'take_off_at': '2020-07-23 09:01:20 +0000',
            'price': {
                'code': 'GBP',
                'value': '425.50'
            },
        },
        'source': 'ba'
    }
]

cheapest = min(
    results,
    key=lambda x: (
        int(x['data']['price_fractional']) / 100 if 'price_fractional' in x['data'] else
        float(x['data']['price']['value']) if isinstance(x['data'].get('price'), dict) and x['data']['price'].get('code') == 'GBP' else
        int(''.join(filter(str.isdigit, x['data']['price']))) if isinstance(x['data'].get('price'), str) and 'GBP' in x['data']['price'] else
        float('inf')
    )
)

print("Cheapest option:")
print(f"Source: {cheapest['source']}")
print("Price:", (
    int(cheapest['data']['price_fractional']) / 100 if 'price_fractional' in cheapest['data'] else
    float(cheapest['data']['price']['value']) if isinstance(cheapest['data'].get('price'), dict) and cheapest['data']['price'].get('code') == 'GBP' else
    int(''.join(filter(str.isdigit, cheapest['data']['price']))) if isinstance(cheapest['data'].get('price'), str) and 'GBP' in cheapest['data']['price'] else
    'N/A'
))


