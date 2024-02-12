package com.progneo.smarthealth.domain.usecase

import com.progneo.smarthealth.domain.model.HeartRateRecord
import com.progneo.smarthealth.domain.repository.HeartRateRemoteRepository
import javax.inject.Inject

class SendHeartRateRecordListUseCase @Inject constructor(
    private val heartRateRepository: HeartRateRemoteRepository
) {

    suspend operator fun invoke(list: List<HeartRateRecord>) =
        heartRateRepository.sendHeartRateRecordList(list)
}
