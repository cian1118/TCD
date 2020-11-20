import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn import preprocessing

# reading in data with numpy and pandas
# dataset id:16-280-64
df = pd.read_csv("week1.csv", comment='#', header=None)
print(df.head())
X = np.array(df.iloc[:, 0]); X = X.reshape(-1, 1)
y = np.array(df.iloc[:, 1]); y = y.reshape(-1, 1)
# print(X)
# print(y)


# normalising data (vanilla python)
def normalise(data):
    mean = sum(data)/len(data)  # shift
    # scale_fac = max(data) - min(data)  # max-min as scale factor range 0 to 1
    scale_fac = (1/len(data) * sum([(i - mean)**2 for i in data]))**0.5  # std deviation as scale factor range -1 to 1
    norm_data = np.zeros(shape=(len(data), 1))
    for i in range(len(data)):
        norm_elem = (data[i] - mean) / scale_fac
        norm_data[i] = norm_elem
    return norm_data


norm_X = normalise(X)
norm_y = normalise(y)

# using mean of normalised y values for
baseline_model = sum(norm_y)/len(norm_y)
print("base:", baseline_model)
m = len(norm_X)
max_iter = 800


def gradient_descent(alpha):
    costs = []
    theta0 = 0
    theta1 = 0

    for index in range(max_iter):
        pred = theta0 + (theta1 * norm_X)  # predictions h(x)

        # gradient descent algorithm
        # calculating delta0 and delta1
        delta0 = (-2/m) * np.sum(norm_y - pred)
        delta1 = (-2/m) * np.sum((norm_y - pred) * norm_X)

        # updating theta0 and theta1 with the above calculated values
        theta0 = theta0 - (alpha * delta0)
        theta1 = theta1 - (alpha * delta1)

        cost_func = (1/m) * np.sum((norm_y - pred)**2)  # cost function calculation
        costs.append(cost_func)  # list of cost function results
        # print(index, theta0, theta1)
    pred = theta0 + (theta1 * norm_X)
    print("Parameter values:\nTheta0=", theta0, "  Theta1=", theta1)
    print("Cost function val=", costs[-1])
    return costs, pred


cost1, pred1 = gradient_descent(0.1)
cost2, pred2 = gradient_descent(0.01)
cost3, pred3 = gradient_descent(0.001)

# Plotting data points and predictions
plt.rc('font', size=18)
plt.scatter(norm_X, norm_y, color='black')
plt.plot([min(norm_X), max(norm_X)], [min(pred1), max(pred1)], color='blue', linewidth=3)
plt.axhline(y=baseline_model, color='r', linewidth=3)
plt.title("Vanilla Python: Linear Regression Model", size=18)
plt.xlabel("input x")
plt.ylabel("output y")
plt.legend(["predictions", "baseline model", "training data"])
plt.show()

# Plotting cost function results and iterations
plt.plot(list(range(max_iter)), cost1)
plt.plot(list(range(max_iter)), cost2, color='red')
plt.plot(list(range(max_iter)), cost3, color='green')
plt.title("Cost Function and Iterations")
plt.xlabel("iteration")
plt.ylabel("cost function J(theta)")
plt.legend(["alpha = 0.1", "alpha = 0.01", "alpha = 0.001"])
plt.show()

# using sklearn to train a linear regression model on the data
X_scaled = preprocessing.scale(X)
y_scaled = preprocessing.scale(y)

model = LinearRegression()
model.fit(X_scaled, y_scaled)
model_y_pred = model.predict(X_scaled)
print(model.intercept_, model.coef_)

plt.scatter(X_scaled, y_scaled,  color='black')
plt.plot(X_scaled, model_y_pred, color='blue', linewidth=3)
plt.title("sklearn: Linear Regression Model")
plt.rc('font', size=18)
plt.xlabel("input x")
plt.ylabel("output y")
plt.legend(["predictions", "training data"])
plt.show()
