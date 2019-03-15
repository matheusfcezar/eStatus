# #==================== Building Stage ================================================ 

# # Create the image based on the official Node 8.9.0 image from Dockerhub
FROM node:9.11.1 as node

# # Create a directory where our app will be placed. This might not be necessary
RUN mkdir -p /ui

# # Change directory so that our commands run inside this new directory
WORKDIR /ui

# # Copy dependency definitions
COPY package.json /ui

RUN npm i npm@latest -g
# # Install dependencies using npm
RUN npm install

# # Get all the code needed to run the app
COPY . /ui

# # Expose the port the app runs in
EXPOSE 4200

# #Build the app
RUN npm run build

#==================== Setting up stage ==================== 
# Create image based on the official nginx
FROM nginx:1.13

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From 'builder' stage copy over the artifacts in dist folder to default nginx public folder
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=node ui/dist/ui /usr/share/nginx/html/estatus

CMD ["nginx", "-g", "daemon off;"]