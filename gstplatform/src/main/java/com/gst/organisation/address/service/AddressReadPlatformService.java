package com.gst.organisation.address.service;

import java.util.List;

import com.gst.infrastructure.core.data.EnumOptionData;
import com.gst.infrastructure.core.service.Page;
import com.gst.organisation.address.data.AddressData;
import com.gst.organisation.address.data.AddressLocationDetails;
import com.gst.organisation.address.data.CityDetailsData;
import com.gst.organisation.address.data.CountryDetails;

public interface AddressReadPlatformService {


	List<AddressData> retrieveSelectedAddressDetails(String selectedname);

	List<AddressData> retrieveAddressDetailsBy(Long clientId, String addressType);

	List<AddressData> retrieveAddressDetails();
	
	List<String> retrieveCountryDetails();

	List<String> retrieveStateDetails();

	List<String> retrieveCityDetails();

	List<AddressData> retrieveCityDetails(String selectedname);

	//List<EnumOptionData> addressType();

	AddressData retrieveAdressBy(String cityName);

	List<CountryDetails> retrieveCountries();
	
	List<AddressData> retrieveClientAddressDetails(Long clientId);
	
	Page<AddressLocationDetails> retrieveAllAddressLocations(SearchSqlQuery searchAddresses);

	List<CityDetailsData> retrieveCitywithCodeDetails();

	List<CityDetailsData> retrieveAddressDetailsByCityName(String cityName);
	

}

