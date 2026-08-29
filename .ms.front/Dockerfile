FROM node:lts-trixie-slim AS build
WORKDIR /front
RUN yarn install
COPY . .
RUN npm install @vue/cli-service --save-dev
RUN yarn run

FROM nginx:latest AS serve
COPY --from=build /front/dist /usr/share/nginx/html
COPY ./frontnginx/server.conf /etc/nginx/conf.d/default.conf
EXPOSE 89
CMD ["nginx", "-g", "daemon off;"]