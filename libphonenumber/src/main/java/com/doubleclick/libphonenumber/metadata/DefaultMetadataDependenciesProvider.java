/*
 * Copyright (C) 2022 The Libphonenumber Authors
 * Copyright (C) 2022 Michael Rozumyanskiy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.doubleclick.libphonenumber.metadata;


import com.doubleclick.libphonenumber.MetadataLoader;
import com.doubleclick.libphonenumber.metadata.init.ClassPathResourceMetadataLoader;
import com.doubleclick.libphonenumber.metadata.init.MetadataParser;
import com.doubleclick.libphonenumber.metadata.source.FormattingMetadataSource;
import com.doubleclick.libphonenumber.metadata.source.FormattingMetadataSourceImpl;
import com.doubleclick.libphonenumber.metadata.source.MetadataSource;
import com.doubleclick.libphonenumber.metadata.source.MetadataSourceImpl;
import com.doubleclick.libphonenumber.metadata.source.MultiFileModeFileNameProvider;
import com.doubleclick.libphonenumber.metadata.source.PhoneMetadataFileNameProvider;
import com.doubleclick.libphonenumber.metadata.source.RegionMetadataSource;
import com.doubleclick.libphonenumber.metadata.source.RegionMetadataSourceImpl;

/**
 * Provides metadata init and source dependencies when metadata is stored in multi-file mode and
 * loaded as a classpath resource.
 */
public final class DefaultMetadataDependenciesProvider {

  private static final DefaultMetadataDependenciesProvider INSTANCE = new DefaultMetadataDependenciesProvider();

  public static DefaultMetadataDependenciesProvider getInstance() {
    return INSTANCE;
  }

  public DefaultMetadataDependenciesProvider() {
    this(new ClassPathResourceMetadataLoader());
  }

  public DefaultMetadataDependenciesProvider(MetadataLoader metadataLoader) {
    if (metadataLoader == null) {
      throw new IllegalArgumentException("metadataLoader could not be null.");
    }
    this.metadataLoader = metadataLoader;
    this.phoneNumberMetadataSource =
        new MetadataSourceImpl(
            phoneNumberMetadataFileNameProvider,
            metadataLoader,
            metadataParser);
    this.shortNumberMetadataSource =
        new RegionMetadataSourceImpl(
            shortNumberMetadataFileNameProvider,
            metadataLoader,
            metadataParser);
    this.alternateFormatsMetadataSource =
        new FormattingMetadataSourceImpl(
            alternateFormatsMetadataFileNameProvider,
            metadataLoader,
            metadataParser);
  }

  private final MetadataParser metadataParser = MetadataParser.newLenientParser();
  private final MetadataLoader metadataLoader;

  private final PhoneMetadataFileNameProvider phoneNumberMetadataFileNameProvider =
      new MultiFileModeFileNameProvider(
          "/io/michaelrocks/libphonenumber/android/data/PhoneNumberMetadataProto");
  private final MetadataSource phoneNumberMetadataSource;

  private final PhoneMetadataFileNameProvider shortNumberMetadataFileNameProvider =
      new MultiFileModeFileNameProvider(
          "/io/michaelrocks/libphonenumber/android/data/ShortNumberMetadataProto");
  private final RegionMetadataSource shortNumberMetadataSource;

  private final PhoneMetadataFileNameProvider alternateFormatsMetadataFileNameProvider =
      new MultiFileModeFileNameProvider(
          "/io/michaelrocks/libphonenumber/android/data/PhoneNumberAlternateFormatsProto");
  private final FormattingMetadataSource alternateFormatsMetadataSource;

  public MetadataParser getMetadataParser() {
    return metadataParser;
  }

  public MetadataLoader getMetadataLoader() {
    return metadataLoader;
  }

  public PhoneMetadataFileNameProvider getPhoneNumberMetadataFileNameProvider() {
    return phoneNumberMetadataFileNameProvider;
  }

  public MetadataSource getPhoneNumberMetadataSource() {
    return phoneNumberMetadataSource;
  }

  public PhoneMetadataFileNameProvider getShortNumberMetadataFileNameProvider() {
    return shortNumberMetadataFileNameProvider;
  }

  public RegionMetadataSource getShortNumberMetadataSource() {
    return shortNumberMetadataSource;
  }

  public PhoneMetadataFileNameProvider getAlternateFormatsMetadataFileNameProvider() {
    return alternateFormatsMetadataFileNameProvider;
  }

  public FormattingMetadataSource getAlternateFormatsMetadataSource() {
    return alternateFormatsMetadataSource;
  }

  public String getCarrierDataDirectory() {
    return "/io/michaelrocks/libphonenumber/android/carrier/data/";
  }

  public String getGeocodingDataDirectory() {
    return "/io/michaelrocks/libphonenumber/android/geocoding/data/";
  }
}
