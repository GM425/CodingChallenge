
FROM mysql
ENV MYSQL_DATABASE EMPLOYEE_DB
# ENV MYSQL_ROOT_DATABASE employeeDB
ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_USER user
ENV MYSQL_PASSWORD user
# ENV MYSQL_TCP_PORT 3308
# ENV SQL_INTERNAL_PORT 3308

COPY employeeData.sql /docker-entrypoint-initdb.d

EXPOSE 3306

# ADD data.sql /docker-entrypoint-initdb.d/
# CMD ["mysqld"]


# FROM mysql:latest
# ENV MYSQL_DATABASE EMPLOYEE_DB
# ENV MYSQL_ROOT_PASSWORD root
# COPY data.sql /docker-entrypoint-initdb.d

# If you don't use default
# COPY my.cnf /etc/mysql/conf.d/

# EXPOSE 3306
# In Dockerfile, the FROM instruction specifies the parent image that the current image is built upon. This means that the Docker image being created is based on another image that exists in the Docker registry or is locally available.

# The parent image is essentially a starting point that provides a base file system and environment to build upon. It may include an operating system, application runtime, dependencies, and other components that are required by the application being built.

# By specifying a parent image with FROM, Docker can reuse layers from that image if they haven't changed, which can significantly reduce the time and bandwidth required to build and distribute Docker images.

# For example, if you want to create a Docker image for a Python web application, you can start with a parent image that includes Python and the necessary libraries and dependencies for your application. You would use the FROM instruction to specify this parent image at the beginning of your Dockerfile. Then, you can add your own application code and other configuration settings to create a custom image tailored to your specific needs.
# /////////////////////////
# In a Dockerfile for a MySQL container, the MYSQL_ROOT_PASSWORD and MYSQL_PASSWORD environment variables serve different purposes.

# The MYSQL_ROOT_PASSWORD environment variable is used to set the password for the root user of the MySQL database. The root user is the superuser in the MySQL system and has all privileges to manage the database. This variable is required when you first start the MySQL container, and it is recommended to set a strong password for security purposes.

# The MYSQL_PASSWORD environment variable, on the other hand, is used to set the password for a non-root user with full privileges on a specific database. This non-root user can be used for specific application purposes and has fewer privileges than the root user. It is not required to set this variable, and if it is not set, the container will not create a non-root user.

# So, the main difference between MYSQL_ROOT_PASSWORD and MYSQL_PASSWORD is the user they are used to set the password for. The former is used for the root user, while the latter is used for a non-root user.
# /////////////////////////

# Setting both environment variables ENV MYSQL_TCP_PORT 3308 and ENV SQL_INTERNAL_PORT 3308 in a Dockerfile may seem redundant, as they both appear to set the same value for different variables. However, it is possible that there is a valid reason for doing so.

# ENV MYSQL_TCP_PORT 3308 sets the environment variable MYSQL_TCP_PORT to the value 3308. This variable is used by the MySQL client to determine which port to use when connecting to the MySQL server.

# ENV SQL_INTERNAL_PORT 3308 sets the environment variable SQL_INTERNAL_PORT to the value 3308. This variable may be used by other parts of the application or system to identify the port number that the MySQL server is listening on.

# It's possible that these environment variables are being used by different parts of the application or system, and using separate variable names helps to avoid confusion or conflicts. Alternatively, it may simply be a matter of personal preference or coding style.

# In any case, it is generally safe to include both environment variables in the Dockerfile, even if they appear to be redundant, as it should not cause any harm or issues in the functioning of the application.
# /////////////////////////


# In a Dockerfile, the EXPOSE instruction is used to inform Docker that a container will listen on a specific network port at runtime. This instruction does not actually publish the port or make it accessible from outside the container; it simply documents the intended use of the port.

# By using the EXPOSE instruction, you can provide documentation to other developers, operators, or users of your container, indicating which ports are used by the application running inside the container. It also provides a hint to Docker that the container will need network connectivity, and it will therefore configure the container's network accordingly.

# When you run a Docker container, you can use the -p flag to publish a container's ports to the host machine, making them accessible from the outside. If you do not specify a port with -p, Docker will not publish any ports to the host machine, even if they are exposed in the Dockerfile.

# In summary, EXPOSE in a Dockerfile is used to document the intended use of a network port in the container, while -p at runtime is used to publish the port and make it accessible from outside the container.