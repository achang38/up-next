import pandas as pd

names = pd.read_csv('movies.csv',encoding='utf-8')
names = names[['imdb_title_id','title','description','date_published','genre','duration','country','writer','worlwide_gross_income','director','language','actors']]
newnames = names.fillna(0)
print(names.columns.tolist())
newnames.to_csv('moviesorig.csv',index=False)