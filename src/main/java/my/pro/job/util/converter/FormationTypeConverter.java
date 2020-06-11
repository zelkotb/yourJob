package my.pro.job.util.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import my.pro.job.enumeration.FormationType;

@Converter(autoApply = true)
public class FormationTypeConverter implements AttributeConverter<FormationType, String>{

	@Override
	public String convertToDatabaseColumn(FormationType attribute) {
		return attribute==null? null : attribute.getType();
	}

	@Override
	public FormationType convertToEntityAttribute(String dbData) {
		 return Stream.of(FormationType.values())
				.filter(f -> f.getType().equals(dbData))
				.findFirst()
				.orElseThrow(
					()-> new IllegalArgumentException(dbData+" does not exist"));
	}

}
