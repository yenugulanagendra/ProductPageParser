package uk.co.sainsbury.test.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.service.JsonBuilderService;


/**
 * Implementation of Json Object
 */
public class DefaultJsonBuilderService implements JsonBuilderService
{
	@Override
	public String buildJsonObject(final Results results)
	{

		String writeValueAsString = null;

		ObjectMapper mapper = new ObjectMapper();

		if (mapper.canSerialize(Results.class))
		{
			try
			{
                writeValueAsString =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			}
			catch (JsonProcessingException e)
			{
				System.err.println("Error processing Json " + e.getMessage());
			}
			catch (IOException e)
			{
				System.err.println("Error processing Json " + e.getMessage());
			}
		}
		return writeValueAsString;
	}
}
