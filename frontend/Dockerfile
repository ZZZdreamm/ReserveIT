# FROM node:16 as build

# WORKDIR /app
# COPY package.json .
# COPY package-lock.json .

# RUN npm install
# COPY . .
# RUN npm run build

# FROM nginx:alpine as production

# COPY --from=build /app/.next/ /usr/share/nginx/html
# COPY nginx.conf /etc/nginx/conf.d/

# EXPOSE 80

# CMD ["nginx", "-g", "daemon off;"]





# using staged builds
# FROM node:18-buster as builder
# # make the directory where the project files will be stored
# RUN mkdir -p /usr/src/next-nginx
# # set it as the working directory so that we don't need to keep referencing it
# WORKDIR /usr/src/next-nginx
# # Copy the package.json file
# COPY package.json package.json
# # install project dependencies
# RUN npm install
# # copy project files
# # make sure to set up .dockerignore to copy only necessary files
# COPY . .
# # run the build command which will build and export html files
# RUN npm run build

# # bundle static assets with nginx
# FROM nginx:1.21.0-alpine as production
# ENV NODE_ENV production
# # remove existing files from nginx directory
# RUN rm -rf /usr/share/nginx/html/*
# # copy built assets from 'builder' stage
# COPY --from=builder /usr/src/next-nginx/out /usr/share/nginx/html
# # add nginx config
# COPY nginx.conf /etc/nginx/conf.d/default.conf
# # expose port 80 for nginx
# EXPOSE 80
# # start nginx
# CMD ["nginx", "-g", "daemon off;"]




# FROM node:18-alpine as base
# RUN apk add --no-cache g++ make py3-pip libc6-compat
# WORKDIR /app
# COPY package*.json ./
# RUN npm install
# RUN mkdir -p /app/node_modules /app/.next
# EXPOSE 3000


# FROM base as builder
# WORKDIR /app
# RUN npm install
# COPY . .
# RUN npm run build


# FROM base as production
# WORKDIR /app

# ENV NODE_ENV=production

# COPY --from=builder --chown=nextjs:nodejs /app/.next ./.next
# COPY --from=builder /app/node_modules ./node_modules
# COPY --from=builder /app/package.json ./package.json
# COPY --from=builder /app/public ./public

# CMD npm run start

# FROM base as dev
# ENV NODE_ENV=development
# RUN npm install
# COPY . .
# CMD npm run dev


FROM node:16 as build

WORKDIR /app
COPY package.json .
COPY package-lock.json .

RUN npm install
COPY . .
RUN npm run build

EXPOSE 3000

CMD npm run start