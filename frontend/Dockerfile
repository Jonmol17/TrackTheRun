FROM node:20-alpine AS base
WORKDIR /app

COPY package.json package-lock.json ./ 
RUN npm ci --legacy-peer-deps

COPY . . 
RUN apk add --no-cache libc6-compat

FROM base AS build 
ENV NODE_ENV=production 
RUN npm run build

FROM node:20-alpine AS production 
WORKDIR /app

ENV NODE_ENV=production

COPY package.json package-lock.json ./ 
RUN npm ci --omit=dev --legacy-peer-deps

COPY --from=build /app/.next ./.next 
COPY --from=build /app/public ./public 

EXPOSE 3000 
CMD ["npm", "run", "start"]
