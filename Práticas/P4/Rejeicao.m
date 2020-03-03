function [Xr,Yr] = Rejeicao(a,b,N)

X = (b-a)*rand(1,N)+a;
Y = rand(1,N);
fX = 1/(sqrt(2*pi))*exp(-X.^2 / 2);

Xr = X(Y<=fX);
Yr = Y(Y<=fX);

end