package com.novare.minet.service;

import com.novare.minet.model.Transaction;

public interface IAdminService extends IBaseService {

	Transaction reviewTransactions(Transaction transaction);

}
