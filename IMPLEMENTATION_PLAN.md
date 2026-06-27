# Festival Copilot Implementation Plan

## 1. Product Scope

Build a festival assistant that helps attendees and organizers with:

- Personalized event recommendations
- Conflict-free schedule planning
- Crowd prediction and congestion avoidance
- Budget-aware food and event suggestions
- Nearby accommodation and restaurant discovery
- Organizer analytics dashboards

## 2. Proposed Architecture

### Backend

- Java 17+
- Spring Boot REST API
- Spring Data JPA for persistence
- PostgreSQL for relational storage
- Scheduled jobs for forecasting and analytics refresh

### Client

- Web or desktop client for attendees
- Separate organizer dashboard view
- Responsive UI for mobile-friendly usage

### External Services

- Google Maps API or OpenStreetMap for geolocation, routing, and place lookup
- Optional payment or wallet integration if transaction tracking is needed

## 3. Core Modules

### A. Authentication and User Profiles

- Register and log in users
- Store interests, budget, preferred genres, and favorite venues
- Track prior interactions and saved items

### B. Event Recommendation Engine

- Use content-based filtering on event tags, category, location, and time
- Rank results using score weights for interest match, distance, budget fit, and popularity
- Return top-N recommendations with explanations

### C. Schedule Planner

- Model events as time slots with constraints
- Detect overlaps using a constraint-based approach
- Generate a conflict-free itinerary per user
- Allow manual edits and re-optimization

### D. Crowd Prediction

- Ingest venue attendance history and current signals
- Forecast near-term crowd levels with ARIMA or a simpler baseline if data is limited
- Flag high-risk venues and suggest alternate times or nearby events

### E. Budget Assistant

- Compare user budget with event and food costs
- Rank items by value score within budget
- Track spent vs remaining budget
- Recommend affordable combinations of events, food, and transport

### F. Navigation and Discovery

- Find nearby restaurants, accommodation, and venues
- Compute route distance using Haversine for proximity ranking
- Use shortest-path logic for venue navigation where map data is available

### G. Organizer Analytics Dashboard

- Show ticket sales, revenue, occupancy, and crowd trends
- Provide charts for venue usage and vendor performance
- Support real-time refresh or periodic updates

## 4. Data Model

### Main Tables

- `users`
- `user_preferences`
- `events`
- `event_tags`
- `user_event_interactions`
- `schedules`
- `schedule_items`
- `crowd_snapshots`
- `budget_profiles`
- `transactions`
- `venues`
- `locations`
- `recommendations`
- `analytics_snapshots`

### Key Relationships

- A user has many preferences, interactions, schedules, and budget records
- An event belongs to one venue and may have many tags
- A schedule contains ordered schedule items
- Crowd snapshots belong to venues and timestamps
- Analytics snapshots aggregate venue and event metrics

## 5. API Surface

### User APIs

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/users/{id}`
- `PUT /api/users/{id}/preferences`

### Recommendation APIs

- `GET /api/recommendations/events?userId=...`
- `GET /api/recommendations/budget?userId=...`
- `GET /api/recommendations/nearby?userId=...`

### Scheduling APIs

- `POST /api/schedules/generate`
- `GET /api/schedules/{userId}`
- `PUT /api/schedules/{scheduleId}`

### Crowd APIs

- `GET /api/crowd/forecast?venueId=...`
- `GET /api/crowd/status?venueId=...`

### Navigation APIs

- `GET /api/navigation/route?from=...&to=...`
- `GET /api/navigation/nearby?lat=...&lng=...&type=...`

### Organizer APIs

- `GET /api/analytics/overview`
- `GET /api/analytics/revenue`
- `GET /api/analytics/occupancy`
- `GET /api/analytics/vendor-performance`

## 6. OOP Design

### Domain Classes

- `User`
- `Event`
- `Venue`
- `Schedule`
- `ScheduleItem`
- `Recommendation`
- `CrowdSnapshot`
- `BudgetProfile`
- `Transaction`

### Service Layer

- `UserService`
- `RecommendationService`
- `ScheduleService`
- `CrowdPredictionService`
- `BudgetService`
- `NavigationService`
- `AnalyticsService`

### Strategy and Helper Classes

- `RecommendationStrategy`
- `ContentBasedRecommendationStrategy`
- `ScheduleOptimizer`
- `CrowdForecaster`
- `RouteCalculator`
- `BudgetRanker`

## 7. Implementation Order

### Phase 1: Foundation

- Set up Spring Boot project structure
- Configure PostgreSQL and JPA entities
- Build auth and profile APIs

### Phase 2: Core Intelligence

- Implement recommendation ranking
- Implement schedule conflict detection
- Add budget filtering and ranking

### Phase 3: Prediction and Navigation

- Add crowd forecasting
- Add nearby discovery and routing
- Connect Google Maps or OpenStreetMap services

### Phase 4: Analytics Dashboard

- Build organizer metrics endpoints
- Add chart-ready response payloads
- Create dashboard views

### Phase 5: Refinement

- Add validation and error handling
- Add tests for services and ranking logic
- Tune performance and caching

## 8. Success Criteria

- Recommendations reflect user interests and budget constraints
- Schedule generation avoids event clashes
- Crowd alerts are returned in time to be useful
- Organizer dashboard exposes real-time operational metrics
- API response time stays near the slide target of under 2 seconds for common requests

## 9. Recommended First Build

Start with the smallest usable version:

1. User profile and event CRUD
2. Content-based recommendations
3. Basic schedule planner
4. Budget-aware ranking
5. Simple analytics dashboard

That version is enough to demonstrate the project end to end before adding forecasting and map integrations.