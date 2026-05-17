package com.college.website.repository;

import com.college.website.entity.ContactMessage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository
        extends JpaRepository<ContactMessage, Long> {
}