# Use official nginx image
FROM nginx:latest

# Copy custom nginx config (optional)
COPY nginx.conf /etc/nginx/nginx.conf

# Copy website files
COPY html/ /usr/share/nginx/html/

# Expose HTTP port
EXPOSE 80

# Start nginx in foreground
CMD ["nginx", "-g", "daemon off;"]
