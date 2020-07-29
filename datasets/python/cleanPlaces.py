import pandas as pd
import unicodedata

# if utf-8 still leaves poor data, try utf-16
file_fq_path = 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\worldcitiespopRef.csv'
df = pd.read_csv(file_fq_path, delimiter=",", encoding='utf-8')

df.info()  # gets the dataframe information including column dtypes

# Clean the data
df.head()
df.Population = df.Population.fillna('0')
df.head()
# Drop irrelevant column
df = df.drop('AccentCity', 1)
df.head()
# Drop duplicates based on uniqueness of Country, City data pair
df.drop_duplicates(subset=['Country', 'City'], keep='first', inplace=True)
df.head()
# Round the Latitude/Longitude to reduced overflow errors that where occurring
df.round(6)
# update to csv
df.to_csv('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/worldcitiesCleaned.csv', float_format='%.6f', index=False,
          encoding='utf-8')

# This is to strictly set the data types for the dataframe
# import pandas as pd
file_fq_path = 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\worldcitiespopRef.csv'
df = pd.read_csv(
    file_fq_path, delimiter=",", encoding='utf-8',
    dtype={'Country': str, 'City': str, 'AccentCity': str, 'Region': str, 'Population': float, 'Latitude': float,
           'Longitude': float}
)

# For formatting the data as ascii values only
text = df.to_string()
text = unicodedata.normalize('NFKD', text).encode('ASCII', 'ignore')
# Actually applying unicode formatting to ASCII
df['City'].apply(lambda val: unicodedata.normalize('NFKD', val).encode('ascii', 'ignore').decode())
