package com.example.demo.demoService;

public interface AuditLoggingService {
	
	void setException(String activity,String Exception,String description);
	void setAuditLog(String activity,String requestBody,String description);

}
