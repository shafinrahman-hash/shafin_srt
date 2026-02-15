def odd_even(number):
    if number % 2 == 0:
        print ("The number", number, "is even")
    else:
        print ("The number", number, "is odd")

num = int(input("Enter the number: "))
odd_even(num)