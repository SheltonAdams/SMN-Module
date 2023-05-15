# base image
FROM node:14.17.0-alpine

# set working directory
WORKDIR /smn-module


# install dependencies
RUN npm install

# copy app files
COPY package*.json /smn-module/
COPY angular.json /smn-module/
COPY src /Supervisor-Module/src/


# build app
RUN npm run build --prod

# expose port
EXPOSE 4200

# start app
CMD ["npm", "run", "start"]
