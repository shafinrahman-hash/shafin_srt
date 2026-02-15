def print_even_number():
    number = 1
    while(True):
        if (number == 101):
            break
        else:
            if (number % 2 == 0):
                print (number)
        number = number + 1

print_even_number()