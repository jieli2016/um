package io.rulai.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JacksonUtils {
	private static class TrimmedJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {
		protected PropertyCollection properties;

		public TrimmedJacksonAnnotationIntrospector(PropertyCollection properties) {
			super();
			this.properties = properties;
		}

		@Override
		protected boolean _isIgnorable(Annotated a) {
			if (a instanceof AnnotatedField) {
				AnnotatedField af = (AnnotatedField) a;
				Field field = af.getAnnotated();
				boolean ignored = properties.containsField(field);
				if (ignored) {
					return true;
				}
			} else if (a instanceof AnnotatedMethod) {
				AnnotatedMethod am = (AnnotatedMethod) a;
				Method method = am.getAnnotated();
				boolean ignored = properties.containsMethod(method);
				if (ignored) {
					return true;
				}
			}
			return super._isIgnorable(a);
		}

	}

	static final ObjectMapper jsonObjectMapper = new ObjectMapper();
	static final ObjectMapper completeJsonObjectMapper = new ObjectMapper();
	static final ObjectMapper noneNullObjectMapper = new ObjectMapper();
	static final Map<PropertyCollection, ObjectMapper> trimmedJsonObjectMappers = Collections
			.synchronizedMap(new HashMap<PropertyCollection, ObjectMapper>());
	static {
		completeJsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		completeJsonObjectMapper.setSerializationInclusion(Include.ALWAYS);
		
		noneNullObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		noneNullObjectMapper.setSerializationInclusion(Include.NON_NULL);

		jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonObjectMapper.setSerializationInclusion(Include.NON_DEFAULT);
	}

	private static ObjectMapper getTrimmedObjectMapper(PropertyCollection properties) {
		ObjectMapper trimmedJsonObjectMapper = trimmedJsonObjectMappers.get(properties);
		if (trimmedJsonObjectMapper == null) {
			trimmedJsonObjectMapper = new ObjectMapper();
			trimmedJsonObjectMapper.setSerializationInclusion(Include.NON_DEFAULT);
			trimmedJsonObjectMapper.setAnnotationIntrospector(new TrimmedJacksonAnnotationIntrospector(properties));
			trimmedJsonObjectMappers.put(properties, trimmedJsonObjectMapper);
		}

		return trimmedJsonObjectMapper;
	}

	public static String serialize(Object obj) throws Exception {
		String res = jsonObjectMapper.writeValueAsString(obj);
		return res;
	}

	public static String serializeWithAllProperties(Object obj) throws Exception {
		String res = completeJsonObjectMapper.writeValueAsString(obj);
		return res;
	}

	public static String serializeWithAllNoneNullProperties(Object obj) throws Exception {
		String res = noneNullObjectMapper.writeValueAsString(obj);
		return res;
	}

	public static String serializeWithIgnoredProperties(Object obj, PropertyCollection properties) throws Exception {
		ObjectMapper mapper = getTrimmedObjectMapper(properties);
		if (mapper == null) {
			throw new IllegalStateException("No trimmed mapper for " + properties);
		}
		String res = mapper.writeValueAsString(obj);
		return res;
	}

	@SuppressWarnings("rawtypes")
	public static String serialize(Object obj, Class cls) throws Exception {
		String res = jsonObjectMapper.writerWithType(cls).writeValueAsString(obj);
		return res;
	}

	@SuppressWarnings("rawtypes")
	public static String serializeWithAllProperties(Object obj, Class cls) throws Exception {
		String res = completeJsonObjectMapper.writerWithType(cls).writeValueAsString(obj);
		return res;
	}

	@SuppressWarnings("rawtypes")
	public static String serializeWithAllNoneNullProperties(Object obj, Class cls) throws Exception {
		String res = noneNullObjectMapper.writerWithType(cls).writeValueAsString(obj);
		return res;
	}

	public static <T> T deserialize(String json, Class<T> cls) throws Exception {
		T res = (T) jsonObjectMapper.readValue(json, cls);
		return res;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> deserializeList(String json, Class<T> cls) throws Exception {
		CollectionType t1 = jsonObjectMapper.getTypeFactory().constructCollectionType(List.class, cls);
		return (List<T>) jsonObjectMapper.readValue(json, t1);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> deserialize(String json) throws Exception {
		return JacksonUtils.deserialize(json, Map.class);
	}

	public static void main(String[] args) throws Exception {
//
//		List<Response> responses = new ArrayList<Response>();
//		responses.add(new Response(1, 3, "3", null,true, 3));
//		responses.add(new Response(0, 0, null, "fdasfd",false, 0));
//
//		String json = serialize(responses);
//		System.out.println("json = " + json);
//
//		json = serializeWithAllProperties(responses);
//		System.out.println("json = " + json);
//
//		List<Response> responses2 = deserializeList(json, Response.class);
//		System.out.println("responses2 = " + CoreUtils.toString(responses2));
//
//		json = serialize(responses2);
//		System.out.println("json2 = " + json);
//
//		json = "[]";
//		responses2 = deserializeList(json, Response.class);
//		System.out.println("responses3 = " + CoreUtils.toString(responses2));
	}
}
