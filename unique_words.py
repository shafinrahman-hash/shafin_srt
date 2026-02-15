def count_unique_words(file_path):
    with open(file_path, 'r') as f:
        text = f.read().lower()  # read & make lowercase

    words = text.split()         # split into words
    unique_words = set(words)    # remove duplicates
    return len(unique_words)     # count unique words

# Example usage
log_file = "example.log"
print("Unique words:", count_unique_words(log_file))