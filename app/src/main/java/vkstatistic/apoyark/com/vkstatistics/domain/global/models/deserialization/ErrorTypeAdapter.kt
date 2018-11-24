package vkstatistic.apoyark.com.vkstatistics.domain.global.models.deserialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.vk.sdk.api.VKError
import org.json.JSONObject
import java.lang.reflect.Type

class ErrorTypeAdapter : JsonDeserializer<VKError> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext): VKError? {
        return try {
            VKError(JSONObject(json.toString()))
        } catch (e: JsonParseException) {
            null
        }
    }
}