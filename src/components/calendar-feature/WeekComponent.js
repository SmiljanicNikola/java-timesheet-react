import React from 'react'

export const WeekComponent = () => {
  return (
    <div class="grey-box-wrap">

			<div class="top">
				{/* eslint-disable jsx-a11y/anchor-is-valid */}
				<a class="prev"><i class="zmdi zmdi-chevron-left"></i>previous week</a>
				<span class="center">February 04 - February 10, 2013 (week 6)</span>
				<a class="next">next week<i class="zmdi zmdi-chevron-right"></i></a>
			</div>
			<div class="bottom">
				<ul class="days">
					<li>
						<a>
							<b>Feb 04</b>
							<i>7.5</i>
							<span>monday</span>
						</a>
					</li>
					<li>
						<a>
							<b>Feb 06</b>
							<i>7.5</i>
							<span>tuesday</span>
						</a>
					</li>
					<li>
						<a>
							<b>Feb 06</b>
							<i>7.5</i>
							<span>wednesday</span>
						</a>
					</li>
					<li class="active">
						<a>
							<b>Feb 07</b>
							<i>7.5</i>
							<span>thursday</span>
						</a>
					</li>
					<li>
						<a>
							<b>Feb 08</b>
							<i>7.5</i>
							<span>friday</span>
						</a>
					</li>
					<li>
						<a>
							<b>Feb 09</b>
							<i>0.0</i>
							<span>saturday</span>
						</a>
					</li>
					<li class="last">
						<a>
							<b>Feb 10</b>
							<i>0.0</i>
							<span>sunday</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
  )
}
