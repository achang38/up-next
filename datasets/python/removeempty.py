import pandas as pd

names = pd.read_csv('names.csv',encoding='utf-8')
names = names.drop('birth_name',1)
names = names.drop('height',1)
names = names.drop('bio',1)
names = names.drop('birth_details',1)
names = names.drop('birth_year',1)
names = names.drop('death_details',1)
names = names.drop('death_year',1)
names = names.drop('date_of_death',1)
names = names.drop('place_of_death',1)
names = names.drop('reason_of_death',1)
names = names.drop('spouses',1)
names = names.drop('divorces',1)
names = names.drop('spouses_with_children',1)
names = names.drop('children',1)
names["city"] = names["place_of_birth"].str.split(",").str[0]
names["country"] = names["place_of_birth"].str.split(",").str[-1]
names = names.drop('place_of_birth',1)

newnames = names.fillna("NULL")
print(names.columns.tolist())
newnames.to_csv('newnames.csv',index=False)