package simple_faq.com.domain.utils

import simple_faq.com.domain.Doubt
import simple_faq.com.domain.Response
import java.sql.Timestamp
import kotlin.random.Random

class GlobalTestUtil {
    fun getRandomString(length: Int): String {
        val str = "abcdefghijklmnopqrstuvwxyzABCD@$#*123456789";

        return (1..length)
            .map { str.random() }
            .joinToString("")
    }

    fun buildDoubt(): Doubt {
        return Doubt(
            Random.nextLong(0, 10000),
            getRandomString(Random.nextInt(0, 20)),
            getRandomString(Random.nextInt(0, 20)),
            Timestamp(System.currentTimeMillis()),
            Timestamp(System.currentTimeMillis())
        );
    }

    fun buildDoubt(id: Long, created: Timestamp?): Doubt {
        return Doubt(
            id,
            getRandomString(Random.nextInt(0, 20)),
            getRandomString(Random.nextInt(0, 20)),
            created,
            Timestamp(System.currentTimeMillis())
        );
    }

    fun buildResponse(): Response {
        return Response(
            Random.nextLong(0, 10000),
            Random.nextLong(0, 10000),
            getRandomString(Random.nextInt(0, 20)),
            getRandomString(Random.nextInt(0, 20)),
            Timestamp(System.currentTimeMillis()),
            Timestamp(System.currentTimeMillis())
        );
    }

    fun buildResponse(doubtId: Long): Response {
        return Response(
            Random.nextLong(0, 10000),
            doubtId,
            getRandomString(Random.nextInt(0, 20)),
            getRandomString(Random.nextInt(0, 20)),
            Timestamp(System.currentTimeMillis()),
            Timestamp(System.currentTimeMillis())
        );
    }

    fun buildResponse(id: Long, doubtId: Long, created: Timestamp?): Response {
        return Response(
            id,
            doubtId,
            getRandomString(Random.nextInt(0, 10)),
            getRandomString(Random.nextInt(0, 10)),
            created,
            Timestamp(System.currentTimeMillis())
        );
    }
}