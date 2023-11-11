FROM node:16 as build

WORKDIR /app
COPY package.json .
COPY package-lock.json .

RUN npm install
COPY . .
RUN npm run build


FROM nginx:alpine as production

COPY --from=build /app/dist/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/

EXPOSE 80
EXPOSE 443

CMD ["nginx", "-g", "daemon off;"]
