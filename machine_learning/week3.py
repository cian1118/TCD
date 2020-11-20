import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import Lasso
from sklearn.linear_model import Ridge
from sklearn.model_selection import KFold
from sklearn.metrics import mean_squared_error

# read in data
df = pd.read_csv("week3.csv", comment='#', header=None)
print(df.head())
X1 = df.iloc[:, 0]
X2 = df.iloc[:, 1]
X = np.column_stack((X1, X2))
y = np.array(df.iloc[:, 2])

"""(i)(a) 3d plot
data lies on a curve - half cylinder """
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.set_title("3D Plot of Training Data")
ax.set_xlabel('x1')
ax.set_ylabel('x2')
ax.set_zlabel('prediction')
ax.scatter(X[:, 0], X[:, 1], y)
plt.show()


def make_poly_features(x):
    """
    (i)(b) makes polynomial features
    """
    poly = PolynomialFeatures(degree=5)
    new_x = poly.fit_transform(x)
    return new_x


""" train Lasso on a wide range of C values """
print("\n=== LASSO | L1 PENALTY ===")
polyX = make_poly_features(X)
C_params = [1, 10, 100, 1000, 10000]
lasso_models = []
for C in C_params:
    alpha = 1 / (2 * C)
    lasso = Lasso(alpha=alpha)
    lasso.fit(polyX, y)
    # pred = lasso.predict(polyX)
    lasso_models.append(lasso)
    print(f"C={C},alpha={alpha},intercept={lasso.intercept_},coefs={lasso.coef_}")


def test_plot_models(models):
    Xtest = []
    grid = np.linspace(-2, 2)  # adjusted grid to ensure training data can be seen in plot
    for i in grid:
        for j in grid:
            Xtest.append([i, j])
    Xtest = np.array(Xtest)

    polyXtest = make_poly_features(Xtest)
    for model in models:
        y_pred = model.predict(polyXtest)
        fig1 = plt.figure()
        ax = fig1.add_subplot(111, projection='3d')
        ax.scatter(X[:, 0], X[:, 1], y, label='training data', color='red')
        surface = ax.plot_trisurf(Xtest[:, 0], Xtest[:, 1], y_pred, cmap='coolwarm')
        fig1.colorbar(surface, shrink=0.5, aspect=8, label='predictions / y')
        ax.set_xlabel('x1')
        ax.set_ylabel('x2')
        ax.set_zlabel('y')
        ax.legend()
        alpha_param = model.__getattribute__("alpha")
        c_param = 1 / (2 * alpha_param)
        model_name = type(model).__name__
        ax.set_title(f'{model_name} alpha={alpha_param}, C={c_param}')
        plt.show()


""" (i)(c) 3d plots of the models from prev part """
test_plot_models(lasso_models)

""" (i)(d) under- and over-fitting """

""" (i)(e) repeat b and c for ridge models """
print("\n=== RIDGE | L2 PENALTY ===")
ridge_models = []
ridge_c_params = [0.001, 0.01, 0.1, 1, 10, 100]
for C in ridge_c_params:
    alpha = 1 / (2 * C)
    ridge = Ridge(alpha=alpha)
    ridge.fit(polyX, y)
    ridge_models.append(ridge)
    print(f"C={C},alpha={alpha},intercept={ridge.intercept_},coefs={ridge.coef_}")

test_plot_models(ridge_models)

""" (ii)(a) k fold cross validation, C=1 """
def k_fold_cross_val(k, model):
    k_fold = KFold(n_splits=k)
    print(f"=== KFOLD k={k} ===")
    sq_errs = []
    for train, test in k_fold.split(polyX):
        curr_model = model.fit(polyX[train], y[train])
        y_pred = curr_model.predict(polyX[test])
        sq_err = mean_squared_error(y[test], y_pred)
        sq_errs.append(sq_err)
    mean = np.mean(sq_errs)
    std = np.std(sq_errs)
    print(f"mean={mean}, variance={std}")
    return mean, std


folds = [2, 5, 10, 25, 50, 100]
means = []
std_devs = []
for fold in folds:
    lassoC1 = Lasso(alpha=0.5)
    values = k_fold_cross_val(fold, lassoC1)
    means.append(values[0])
    std_devs.append(values[1])

fig2 = plt.figure()
plt.errorbar(np.log10(folds), means, yerr=std_devs, fmt='.', capsize=5)
plt.plot(np.log10(folds), means, linestyle=':', label='mean', linewidth=2, color='orange')
plt.legend()
plt.title("kFold: Mean & Variance vs No. of Folds (Lasso C=1)")
plt.xlabel("log10(no. of folds)")
plt.ylabel("mean & standard deviation")
plt.show()
"""add legend! labels titles etc to all plots"""

""" (ii)(b) LASSO 10-fold cross validation to choose C-parameter """
# models with C_params
lasso_means = []
lasso_std_devs = []
for lasso_mod in lasso_models:
    results = k_fold_cross_val(10, lasso_mod)
    lasso_means.append(results[0])
    lasso_std_devs.append(results[1])

c_logs = np.log10(C_params)

plt.errorbar(np.log10(C_params), lasso_means, yerr=lasso_std_devs, fmt='.', capsize=5)
plt.plot(c_logs, lasso_means, linestyle=':', label='mean', linewidth=2, color='orange')
plt.legend()
plt.title("Mean & Variance vs C (10-fold, Lasso)")
plt.xlabel("log10(C parameter)")
plt.ylabel("mean & standard deviation")
plt.show()

""" (ii)(d) repeat for RIDGE 10-fold cross validation to choose C-parameter """
ridge_means = []
ridge_std_devs = []
for ridge_mod in ridge_models:
    results = k_fold_cross_val(10, ridge_mod)
    ridge_means.append(results[0])
    ridge_std_devs.append(results[1])

c_logs = np.log10(ridge_c_params)

plt.errorbar(c_logs, ridge_means, yerr=ridge_std_devs, fmt='.', capsize=5)
plt.plot(c_logs, ridge_means, linestyle=':', label='mean', linewidth=2, color='orange')
plt.legend()
plt.title("Mean & Variance vs C (10-fold, Ridge)")
plt.xlabel("log10(C parameter)")
plt.ylabel("mean & standard deviation")
plt.show()
