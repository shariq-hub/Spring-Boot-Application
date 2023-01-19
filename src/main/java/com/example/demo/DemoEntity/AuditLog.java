package com.example.demo.DemoEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "auditLog")
public class AuditLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUDIT_LOG_SEQ")
	@SequenceGenerator(name = "AUDIT_LOG_SEQ", sequenceName = "AUDIT_LOG_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column
	private String request;
	@Column
	private String response;
	
}
