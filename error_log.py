def log_parser(log_file_in,log_file_out):
    with open(log_file_in, 'r') as in_file, open(log_file_out, 'w') as out_file:
#        content=in_file.read()
#        print(content)
        for lines in in_file:
            if "ERROR" in lines:
                print(lines)
                out_file.write(lines)

def main():
    log_file_in="/Users/shafin/Documents/Zscaler/Python/app.log"
    log_file_out="/Users/shafin/Documents/Zscaler/Python/error_app.log"
    log_parser(log_file_in,log_file_out)

if __name__ == "__main__":
    main()