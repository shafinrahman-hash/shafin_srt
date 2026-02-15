def is_palindrome(str1):
    str2 = str1.lower()
    str_rev = str2[::-1]
    if str2 == str_rev:
        print("The string is a palindrome")
    else:
        print("The string is not a palindrome")

str1 = input("Enter the string: ")
is_palindrome(str1)