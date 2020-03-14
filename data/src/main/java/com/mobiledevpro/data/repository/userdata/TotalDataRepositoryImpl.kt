package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.toEntity
import com.mobiledevpro.data.toTotal
import com.mobiledevpro.domain.model.Total
import com.mobiledevpro.domain.totaldata.TotalDataRepository
import com.mobiledevpro.local.database.DatabaseHelper
import com.mobiledevpro.local.database.model.TotalDataEntity
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Repository for Total data screen
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class TotalDataRepositoryImpl(private val databaseHelper: DatabaseHelper) : TotalDataRepository {

    override fun getLocalTotalDataObservable(): Observable<Total> =
            databaseHelper.getTotalDataObservable()
                    .onErrorReturn { TotalDataEntity() }
                    .map(TotalDataEntity::toTotal)

    override fun setLocalTotalData(total: Total): Single<Boolean> =
            Single.just(total)
                    .map(Total::toEntity)
                    .flatMap(databaseHelper::updateTotalData)


    /*
    override fun getUser(): Single<User> =
            databaseHelper.getUser(0)
                    .onErrorReturn { UserEntity() }
                    .map(UserEntity::toUser)

    override fun getUserObservable(): Observable<User> =
            databaseHelper.getUserUpdatesObservable()
                    .onErrorReturn { UserEntity() }
                    .map(UserEntity::toUser)

    override fun setUser(user: User): Single<Boolean> =
            Single.just(user)
                    .map(User::toEntity)
                    .flatMap(databaseHelper::updateUser)


     */
}

