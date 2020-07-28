import pandas as pd

names = pd.read_csv('finalmovies.csv',encoding='utf-8')
names = names.drop('ID2',1)
names = names.fillna(0)
print(names.columns.tolist())
names.to_csv('newmovies.csv',index=False)