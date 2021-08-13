import csv
import sklearn.metrics as metrics
import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from sklearn import metrics
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

dataset = pd.read_csv('Temp_Rain.csv')

print(dataset.head())
print(dataset.describe())

dataset.plot(x='Humidity', y='Chance of Rain', style='o')
plt.title('Влажност наспроти Дожд')
plt.xlabel('Влажност')
plt.ylabel('Количина на дожд')
plt.show()

X = dataset.iloc[:, :-1].values
y = dataset.iloc[:, 1].values


X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.9, random_state=0)
regressor = LinearRegression()
regressor.fit(X_train, y_train)


y_pred = regressor.predict(X_test)
df = pd.DataFrame({'Actual': y_test, 'Predicted': y_pred})

print(df)

print('Mean Absolute Error:', metrics.mean_absolute_error(y_test, y_pred))
print('Mean Squared Error:', metrics.mean_squared_error(y_test, y_pred))
print('Root Mean Squared Error:', np.sqrt(metrics.mean_squared_error(y_test, y_pred)))


################################ MA

for i in range(0,dataset.shape[0]-2):
    dataset.loc[dataset.index[i + 2], 'SMA_WINDOW2'] = np.round(((dataset.iloc[i, 1] + dataset.iloc[i + 1, 1] + dataset.iloc[i + 2, 1]) / 3), 1)

for i in range(0,dataset.shape[0]-3):
    dataset.loc[dataset.index[i+3],'SMA_WINDOW3'] = np.round(((dataset.iloc[i,1]+ dataset.iloc[i+1,1] +dataset.iloc[i+2,1]+dataset.iloc[i+3,1])/4),1)

print(dataset)

plt.figure(figsize=[15,10])
plt.grid(True)
plt.plot(df['Actual'],label='Реални податоци')
plt.plot(df['Predicted'],label='Линеарно предвидување')
plt.plot(dataset['SMA_WINDOW2'],label='SMA 2 Предходни мерења')
plt.plot(dataset['SMA_WINDOW3'],label='SMA 3 Предходни мерења')
plt.legend(loc=2)
plt.show()













