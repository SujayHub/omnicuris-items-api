package com.omnicuris.items.api.app.repository;

import com.omnicuris.items.api.app.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Component
@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
