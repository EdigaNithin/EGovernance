package com.example.eGovernance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.governance.controller.ApplicationController;
import com.governance.entity.Application;
import com.governance.exception.NotFoundException;
import com.governance.service.ApplicationService;
 

 
@ExtendWith(MockitoExtension.class)
class ApplicationControllerTest {
 
    @Mock
    private ApplicationService applicationService;
 
    @InjectMocks
    private ApplicationController applicationController;
 
    @Test
	void getAllApplications_ReturnsOkResponseWithApplications() throws NotFoundException {
        // Arrange
        List<Application> mockApplications = Collections.singletonList(new Application());
        when(applicationService.getAllApplications()).thenReturn(mockApplications);
 
        // Act
        ResponseEntity<List<Application>> responseEntity = applicationController.getAllApplications();
 
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockApplications, responseEntity.getBody());
    }
 
    @Test
    void getApplicationsByUserId_ValidUserId_ReturnsOkResponseWithApplications() throws NotFoundException {
        // Arrange
        Long userId = 1L;
        List<Application> mockApplications = Collections.singletonList(new Application());
        when(applicationService.getApplicationsByUserId(userId)).thenReturn(mockApplications);
 
        // Act
        ResponseEntity<List<Application>> responseEntity = applicationController.getApplicationsByUserId(userId);
 
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockApplications, responseEntity.getBody());
    }
 

}
 