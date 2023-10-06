FROM node:16.16.0 as builder

WORKDIR /frontend

COPY ./vue/package*.json ./
RUN npm install
COPY ./vue ./
RUN npm run build

FROM nginx:alpine
COPY --from=builder /frontend/dist/ /usr/share/nginx/html