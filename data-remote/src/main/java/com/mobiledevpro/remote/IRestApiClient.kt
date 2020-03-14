package com.mobiledevpro.remote

import com.mobiledevpro.remote.model.ApiGetTotalDataNetwork
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for Rest API client
 *
 *
 * Created by Dmitriy V. Chernysh
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
interface IRestApiClient {

    @GET
    fun getTotalConfirmed(
            @Query(value = "f", encoded = true) format: String = "json",
            @Query(value = "where", encoded = true) where: String = "Confirmed>0",
            @Query(value = "returnGeometry", encoded = true) geometry: Boolean = false,
            @Query(value = "spatialRel", encoded = true) spatialRel: String = "esriSpatialRelIntersects",
            @Query(value = "outFields", encoded = true) outFields: String = "*",
            @Query(value = "outStatistics", encoded = true) outStatistics: String = "[{\"statisticType\":\"sum\",\"onStatisticField\":\"Confirmed\",\"outStatisticFieldName\":\"value\"}]",
            @Query(value = "outSR", encoded = true) outSR: Int = 102100,
            @Query(value = "cacheHint", encoded = true) cacheHint: Boolean = true
    ): Single<Response<ApiGetTotalDataNetwork.Response>>

    /*
    @GET
    fun downloadFile(@Url url: String?): Single<Response<ResponseBody?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("login")
    fun login(
            @Body body: RequestBody?): Single<Response<ApiLogin.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("account/{student_id}/change-my-password")
    fun changePass(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Body body: RequestBody?): Single<Response<ApiChangePass.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @FormUrlEncoded
    @POST("account/reset-password")
    fun resetPass(
            @Field("email") email: String?): Single<Response<ApiResetPass.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("statistics/student/{student_id}/total")
    fun getDashboardScores(
            @Path(value = "student_id", encoded = true) studentId: Int): Single<Response<ApiGetDashboardScores.SuccessResponse?>?>?

    @get:GET("product-level")
    @get:Headers("User-Agent: okhttp/2.2")
    val productLevels: Single<Response<Any?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("student/{student_id}/current-level")
    fun setProductLevel(
            @Body body: RequestBody?,
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiSetDashboardProductLevel.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}")
    fun getStudentAccountData(
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiGetStudentAccount.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("statistics/student/{student_id}/performance")
    fun getPerformanceChart(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Query(value = "type", encoded = true) type: String?,
            @Query(value = "filter", encoded = true) filter: String?
    ): Single<Response<ApiGetDashboardPerformanceChart.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("statistics/student/{student_id}/category")
    fun getDashboardSpecialtiesAndTasks(
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiGetDashboardSpecialtiesAndTasks.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("statistics/student/{student_id}/reset/stat")
    fun resetResettableScore(
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiResetResettableScore.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("student/{student_id}")
    fun setExamDate(
            @Body body: RequestBody?,
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiSetExamDate.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam/create/count")
    fun getExamQuestionsCount(
            @Body body: RequestBody?,
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiGetExamQuestionsCount.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam/create/category")
    fun getExamCategories(
            @Body body: RequestBody?,
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiGetExamCategories.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam")
    fun createNewExam(
            @Body body: RequestBody?,
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiCreateExam.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("exam-forms/{student_id}/form/{form_id}/create-exam")
    fun createNewComsaeExam(
            @Body body: RequestBody?,
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "form_id", encoded = true) formId: Int
    ): Single<Response<ApiCreateComsaeExam.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("mob/student/{student_id}/exam/{exam_id}/take")
    fun getExamQuestions(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "exam_id", encoded = true) examId: Int
    ): Single<Response<ApiGetExamQuestions.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("mob/student/{student_id}/exam/{exam_id}")
    fun getReviewExamQuestions(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "exam_id", encoded = true) examId: Int
    ): Single<Response<ApiGetExamQuestions.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/marked-question/{question_id}")
    fun setQuestionMarked(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int
    ): Single<Response<ApiSetQuestionMarked.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @DELETE("student/{student_id}/marked-question/{question_id}")
    fun setQuestionUnMarked(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int
    ): Single<Response<ApiSetQuestionMarked.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("student/{student_id}/guessed-question/{question_id}")
    fun setQuestionGuessed(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int
    ): Single<Response<ApiSetQuestionGuessed.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @DELETE("student/{student_id}/guessed-question/{question_id}")
    fun setQuestionUnGuessed(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int
    ): Single<Response<ApiSetQuestionGuessed.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam-question/{question_id}/strikeout")
    fun setQuestionAnswerStrikeout(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiSetQuestionAnswerStrikeout.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam/{exam_id}/finalize")
    fun setExamStatus(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "exam_id", encoded = true) examId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiSetExamStatus.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("mob/student/{student_id}/exam/{exam_id}/finalize")
    fun setExamStatusFinalize(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "exam_id", encoded = true) examId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiSetExamFinalize.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/exam")
    fun getExamsList(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Query(value = "filters[createdAt:min]", encoded = true) dateFrom: Long?,
            @Query(value = "filters[createdAt:max]", encoded = true) dateTo: Long?,
            @Query(value = "filters[productLevel]", encoded = true) productLevels: String?,
            @Query(value = "filters[score:max]", encoded = true) maxScore: Int,
            @Query(value = "filters[score:min]", encoded = true) minScore: Int,
            @Query(value = "filters[type]", encoded = true) filterType: String?,
            @Query(value = "offset", encoded = true) offset: Int,
            @Query(value = "limit", encoded = true) resultLimit: Int,
            @Query(value = "filters[status]", encoded = true) statuses: String?,
            @Query(value = "order[createdAt]", encoded = true) orderByDate: String?,
            @Query(value = "order[examEntryIndex]", encoded = true) orderByExamId: String?,
            @Query(value = "order[type]", encoded = true) orderByExamType: String?,
            @Query(value = "order[score]", encoded = true) orderByScore: String?,
            @Query(value = "order[status]", encoded = true) orderByExamStatus: String?,
            @Query(value = "with_comsae", encoded = true) withComsae: Boolean
    ): Single<Response<ApiGetExamsList.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/question-note")
    fun getExamsNotesList(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Query(value = "filters[productLevel]", encoded = true) productLevel: String?,
            @Query(value = "filters[primaryCategory]", encoded = true) specialty: String?,
            @Query(value = "filters[secondaryCategory]", encoded = true) task: String?,
            @Query(value = "filters[text]", encoded = true) searchText: String?,
            @Query(value = "filters[date:min]", encoded = true) dateFrom: Long?,
            @Query(value = "filters[date:max]", encoded = true) dateTo: Long?,
            @Query(value = "offset", encoded = true) offset: Int,
            @Query(value = "limit", encoded = true) resultLimit: Int,
            @Query(value = "order[date]", encoded = true) orderByDate: String?
    ): Single<Response<ApiGetExamsNotesList.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @DELETE("student/{student_id}/question/{question_id}/question-note/{note_id}")
    fun deleteQuestionNote(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int,
            @Path(value = "note_id", encoded = true) noteId: Int
    ): Single<Response<ApiUpdateQuestionNote.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("student/{student_id}/question/{question_id}/question-note/{note_id}")
    fun updateQuestionNote(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int,
            @Path(value = "note_id", encoded = true) noteId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiUpdateQuestionNote.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/question/{question_id}/question-note")
    fun createQuestionNote(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiCreateQuestionNote.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("account/{student_id}/question/{question_id}/comment")
    fun sendQuestionComment(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiSetQuestionComment.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/exam/{exam_id}/statistics/results")
    fun getExamStatistics(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "exam_id", encoded = true) examId: Int
    ): Single<Response<ApiGetExamStatistics.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/exam/{exam_id}/statistics/category")
    fun getExamStaticticsCategories(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "exam_id", encoded = true) examId: Int
    ): Single<Response<Array<ApiGetExamStatisticCategories.SuccessResponse.Item?>?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam-question/{question_id}/highlights")
    fun setQuestionHighlights(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiSetQuestionHighlight.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @DELETE("student/{student_id}/exam-question/{question_id}/highlights")
    fun deleteQuestionHighlights(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int
    ): Single<Response<ApiDeleteQuestionHighlight.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/search-question")
    fun getSearchQuestionsList(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Query(value = "filters[productLevel]", encoded = true) productLevel: String?,
            @Query(value = "filters[primaryCategory]", encoded = true) examCategory: String?,
            @Query(value = "filters[answertype]", encoded = true) answerType: String?,
            @Query(value = "filters[text:like]", encoded = true) searchText: String?,
            @Query(value = "limit", encoded = true) resultLimit: Int,
            @Query(value = "offset", encoded = true) offset: Int,
            @Query(value = "order[questionId]", encoded = true) orderByQuestionId: String?
    ): Single<Response<ApiGetSearchQuestionsList.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/marked-question")
    fun getMarkedQuestionsList(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Query(value = "filters[productLevel]", encoded = true) productLevel: String?,
            @Query(value = "filters[primaryCategory]", encoded = true) specialty: String?,
            @Query(value = "filters[answertype]", encoded = true) answerType: String?,
            @Query(value = "limit", encoded = true) resultLimit: Int,
            @Query(value = "offset", encoded = true) offset: Int,
            @Query(value = "order[questionId]", encoded = true) orderByQuestionId: String?
    ): Single<Response<ApiGetMarkedQuestionsList.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("question/{question_secondary_id}")
    fun getQuestionData(
            @Path(value = "question_secondary_id", encoded = true) secondaryId: Int
    ): Single<Response<ApiGetQuestion.Data?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/exam-question/{question_id}/highlights")
    fun getQuestionHighlights(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int
    ): Single<Response<ApiGetQuestionHighlights.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/question/{question_id}/question-note")
    fun getQuestionNotes(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int
    ): Single<Response<ApiGetExamQuestionNotes.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/search-question/categories")
    fun getSearchQuestionCategories(
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiGetSearchQuestionCategories.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/marked-question/categories")
    fun getMarkedQuestionCategories(
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiGetMarkedQuestionCategories.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam/{exam_id}/copy")
    fun createExamCopy(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "exam_id", encoded = true) examId: Int
    ): Single<Response<ApiCreateExamCopy.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/question-note/categories")
    fun getExamsNotesCategories(
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiGetExamsNotesCategories.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/send-to-support")
    fun sendFeedback(
            @Body body: RequestBody?
    ): Single<Response<ApiSendFeedback.SuccessResponse?>?>?

    @get:GET("school-name")
    @get:Headers("User-Agent: okhttp/2.2")
    val schools: Single<Response<Any?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("student/{student_id}")
    fun updateAccount(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiUpdateAccount.SuccessResponse?>?>?

    @AddTrace(name = "Api.GetNotificationsList")
    @Headers("User-Agent: okhttp/2.2")
    @GET("notification")
    fun getNotificationsList(
            @Query(value = "filters[new]", encoded = true) filters: String?,
            @Query(value = "limit", encoded = true) resultLimit: Int,
            @Query(value = "offset", encoded = true) offset: Int,
            @Query(value = "order[createdAt]", encoded = true) createdAtOrder: String?
    ): Single<Response<ApiGetAccountNotifications.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("notification/{notification_id}/mark-as-seen")
    fun setNotificationIsSeen(
            @Path(value = "notification_id", encoded = true) notificationId: Int
    ): Single<Response<ApiSetNotificationSeen.SuccessResponse?>?>?

    @AddTrace(name = "Api.GetMarkedQuestionsStatistics")
    @Headers("User-Agent: okhttp/2.2")
    @GET("student/{student_id}/marked-question/statistics/results")
    fun getMarkedQuestionsStatistics(
            @Path(value = "student_id", encoded = true) studentId: Int
    ): Single<Response<ApiGetMarkedQuestionsStatistics.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam-question/{question_id}/selfscore")
    fun setSelfAssessedScore(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiSetSelfAssessedScore.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @POST("student/{student_id}/exam/{exam_id}/update-segment")
    fun setExamSection(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "exam_id", encoded = true) examId: Int,
            @Body body: RequestBody?
    ): Single<Response<ApiSetExamSection.SuccessResponse?>?>?

    @Headers("User-Agent: okhttp/2.2")
    @PUT("student/{student_id}/exam-question/{question_id}/mark-as-reviewed")
    fun setQuestionReviewed(
            @Path(value = "student_id", encoded = true) studentId: Int,
            @Path(value = "question_id", encoded = true) questionId: Int
    ): Single<Response<ApiSetQuestionReviewed.SuccessResponse?>?>?


     */
}