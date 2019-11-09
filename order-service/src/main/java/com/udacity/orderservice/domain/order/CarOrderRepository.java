package com.udacity.orderservice.domain.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "orders")
public interface CarOrderRepository extends CrudRepository<CarOrder,Long> {
}
