# springJWT
SpringJWTauthentication

# Simple Spring Boot application with JWT authentication. 
- Has an api with get requests and an api with post requests (for registration and login) without need of authorization.
- Has a secured api on which the client has to send the received JWT token via Header to be authorized.
- future features implementations: use roles to authenticate.

# Branches with different features

  1.-Swagger ([add-swagger branch](https://github.com/hodeiez/springJWT/tree/add-swagger)) has an api documentation implemented using swagger
  
  2.-Roles ([add-roles branch](https://github.com/hodeiez/springJWT/tree/add-roles)) different authorities managment via roles. Added a role entity to apply for each user, an user can have multiple roles, and depending on the role has different authority (can request to an api address)
  
