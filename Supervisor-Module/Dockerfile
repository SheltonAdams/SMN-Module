# base image
FROM node:14.17.0-alpine

# set working directory
WORKDIR /app

# copy package.json and package-lock.json
COPY package*.json ./

# install dependencies
RUN npm install

# copy app files
COPY . .

# build app
RUN npm run build --prod

# expose port
EXPOSE 80

# start app
CMD ["npm", "run", "start"]
