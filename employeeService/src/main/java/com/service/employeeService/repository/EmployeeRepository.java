package com.service.employeeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.service.employeeService.model.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
    List<EmployeeEntity> findAll();
    Optional<EmployeeEntity> findByEmail(String email);


}

// When using a CRUD (Create, Read, Update, Delete) repository bean, such as the one provided by Spring Data JPA, the following functions are typically available:

// save(): This function is used to save an entity to the database. If the entity already exists, it will update it; otherwise, it will create a new record in the database.

// findById(): This function is used to retrieve an entity from the database by its ID.

// findAll(): This function is used to retrieve all entities from the database.

// deleteById(): This function is used to delete an entity from the database by its ID.

// deleteAll(): This function is used to delete all entities from the database.

// In addition to these basic functions, Spring Data JPA also provides additional functions that allow you to perform more complex queries, such as sorting, pagination, and filtering. These functions are typically generated automatically based on the names of your entity's properties and follow a specific naming convention.

// For example, if your entity has a property called name, Spring Data JPA will automatically generate a function called findByName() that can be used to retrieve entities that match a specific name. Similarly, if your entity has a property called age, Spring Data JPA will generate functions such as findByAgeGreaterThan(), findByAgeLessThan(), and findByAgeBetween() that can be used to retrieve entities based on their age.


// In Java, a class and an interface are both types of reference data types, but they have some fundamental differences:

// Implementation: A class is an implementation, while an interface is a contract. A class defines a set of methods and properties that an object of that class has, while an interface defines a set of methods that a class must implement. A class can have instance variables, constructors, and methods with implementations, while an interface cannot.

// Inheritance: A class can only extend one other class, but it can implement multiple interfaces. An interface can extend multiple interfaces, but it cannot extend a class.

// Access Modifiers: A class can have any access modifier (public, private, protected, or default), while an interface can only have public or default access modifiers.

// Instantiation: A class can be instantiated using the new keyword to create objects of that class. An interface cannot be instantiated, but a class that implements that interface can be instantiated.

// Method Overriding: A class can override methods from its superclass, and it can also implement methods defined in an interface. An interface cannot override any methods.

// In general, classes are used to define objects that have specific properties and behaviors, while interfaces are used to define contracts that classes must follow. Interfaces are often used in Java to achieve polymorphism and to define common behaviors across multiple classes.

// The code public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long>{ } is defining a Java interface that extends the CrudRepository interface and specifies the EmployeeEntity class as the entity type to be managed by the repository.

// Here's what each part of this code means:

// public interface EmployeeRepository: This declares a public interface named EmployeeRepository.
// extends CrudRepository<EmployeeEntity, Long>: This extends the CrudRepository interface and specifies EmployeeEntity as the entity type to be managed by the repository, and Long as the type of the entity's primary key. CrudRepository is a generic interface provided by Spring Data JPA that defines standard CRUD (Create, Read, Update, Delete) operations that can be performed on entities.
// { }: This defines the body of the interface, which is empty in this case since the interface only inherits methods from CrudRepository.
// By extending CrudRepository, EmployeeRepository inherits a set of methods for performing CRUD operations on the EmployeeEntity class, such as save(), findById(), findAll(), delete(), and others. These methods can be called from the EmployeeRepository instance to interact with the database and perform operations on EmployeeEntity records.