package com.fogueteiras.organizai.data.remote

import com.fogueteiras.organizai.data.Subject
import com.fogueteiras.organizai.data.Task
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrganizaiApiService {
    @GET("tasks")
    suspend fun getTasks(): List<Task>

    @POST("tasks")
    suspend fun createTask(@Body task: Task): Task

    @GET("subjects")
    suspend fun getSubjects(): List<Subject>
}
