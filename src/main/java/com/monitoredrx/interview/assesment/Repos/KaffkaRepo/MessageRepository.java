package com.monitoredrx.interview.assesment.Repos.KaffkaRepo;

import com.monitoredrx.interview.assesment.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}