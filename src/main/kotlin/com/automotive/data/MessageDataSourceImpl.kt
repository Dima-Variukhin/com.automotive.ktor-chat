package com.automotive.data

import com.automotive.data.model.Message
import org.litote.kmongo.coroutine.CoroutineDatabase

class MessageDataSourceImpl(
    private val db: CoroutineDatabase
) : MessageDataSource {

    private val messages = db.getCollection<Message>()

    override suspend fun getAllMessages() =
        messages.find().descendingSort(Message::timestamp).toList()

    override suspend fun insertMessage(message: Message) {
        messages.insertOne(message)
    }
}