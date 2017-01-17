package by.tr.op.service;

import by.tr.op.service.exception.ServiceException;

public interface InitializeConnectionService {
    
    void init() throws ServiceException;
    void destroy() throws ServiceException;
    
}
